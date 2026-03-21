import "./App.css";
import SeatButton from "./components/Seats/SeatButton";

export const seats = [
  { id: "A1", status: "PENDIENTE" },
  { id: "A2", status: "RESERVADO" },
  { id: "A3", status: "LIBRE" },
  { id: "A4", status: "LIBRE" },
  { id: "A5", status: "RESERVADO" },
  { id: "A6", status: "LIBRE" },
  { id: "A7", status: "LIBRE" },
  { id: "A8", status: "RESERVADO" },
  { id: "A9", status: "LIBRE" },
  { id: "A10", status: "LIBRE" },

  { id: "B1", status: "LIBRE" },
  { id: "B2", status: "LIBRE" },
  { id: "B3", status: "RESERVADO" },
  { id: "B4", status: "LIBRE" },
  { id: "B5", status: "LIBRE" },
  { id: "B6", status: "RESERVADO" },
  { id: "B7", status: "LIBRE" },
  { id: "B8", status: "LIBRE" },
  { id: "B9", status: "RESERVADO" },
  { id: "B10", status: "LIBRE" },

  { id: "C1", status: "RESERVADO" },
  { id: "C2", status: "LIBRE" },
  { id: "C3", status: "LIBRE" },
  { id: "C4", status: "LIBRE" },
  { id: "C5", status: "RESERVADO" },
  { id: "C6", status: "LIBRE" },
  { id: "C7", status: "LIBRE" },
  { id: "C8", status: "RESERVADO" },
  { id: "C9", status: "LIBRE" },
  { id: "C10", status: "LIBRE" },

  { id: "D1", status: "LIBRE" },
  { id: "D2", status: "LIBRE" },
  { id: "D3", status: "RESERVADO" },
  { id: "D4", status: "LIBRE" },
  { id: "D5", status: "LIBRE" },
  { id: "D6", status: "RESERVADO" },
  { id: "D7", status: "LIBRE" },
  { id: "D8", status: "LIBRE" },
  { id: "D9", status: "RESERVADO" },
  { id: "D10", status: "LIBRE" },

  { id: "E1", status: "LIBRE" },
  { id: "E2", status: "RESERVADO" },
  { id: "E3", status: "LIBRE" },
  { id: "E4", status: "LIBRE" },
  { id: "E5", status: "RESERVADO" },
  { id: "E6", status: "LIBRE" },
  { id: "E7", status: "LIBRE" },
  { id: "E8", status: "RESERVADO" },
  { id: "E9", status: "LIBRE" },
  { id: "E10", status: "LIBRE" },
];

function App() {
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
