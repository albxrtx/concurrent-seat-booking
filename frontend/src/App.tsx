import { useEffect, useState } from "react";
import "./App.css";
import SeatButton from "./components/Seats/SeatButton";
import type { Seat } from "./types/Seat";

function App() {
  const [seats, setSeats] = useState<Seat[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/seats")
      .then((res) => res.json())
      .then((data) => setSeats(data))
      .catch((err) => console.error("Error al cargar asientos:", err));
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
