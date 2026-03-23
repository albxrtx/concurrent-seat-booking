import { useEffect, useState } from "react";
import "./App.css";
import SeatButton from "./components/Seats/SeatButton";
import type { Seat } from "./types/Seat";

import { connectWebSocket } from "./services/websocket";

function App() {
  const [seats, setSeats] = useState<Seat[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/seats")
      .then((res) => res.json())
      .then((data) => setSeats(data))
      .catch((err) => console.error(err));
  }, []);

  useEffect(() => {
    connectWebSocket((updatedSeat: Seat) => {
      setSeats((prevSeats) =>
        prevSeats.map((seat) =>
          seat.id === updatedSeat.id ? updatedSeat : seat,
        ),
      );
    });
  }, []);
  return (
    <>
      <div className="grid grid-cols-10 gap-6  w-fit items-center justify-center ">
        {seats.map((seat) => {
          return <SeatButton key={seat.id} id={seat.id} status={seat.status} />;
        })}
      </div>
    </>
  );
}

export default App;
