import { useState } from "react";
interface Props {
  id: string;
  status: string;
}
const statusStyles: Record<string, string> = {
  LIBRE: " bg-slate-500  fill-slate-300",
  RESERVADO: "cursor-not-allowed bg-slate-900  fill-slate-300/50",
  PENDIENTE:
    "cursor-not-allowed  bg-violet-200  fill-violet-900 shadow-2xl shadow-violet-200/50",
};

function SeatButton({ id, status }: Props) {
  const [loading, setLoading] = useState(false);
  const reserveSeat = async () => {
    if (loading) return;

    setLoading(true);
    try {
      const res = await fetch(`http://localhost:8080/seats/${id}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id,
        }),
      });

      const data = await res.json();
      console.log("Respuesta del servido", data);
    } catch (error) {
      console.error("Error al hacer POST:", error);
    } finally {
      setLoading(false);
    }
  };
  return (
    <div className="flex flex-col items-center justify-center">
      <button
        onClick={() => reserveSeat()}
        className={`group  flex flex-col gap-1 p-2 rounded shadow ${
          statusStyles[status]
        }`}
        disabled={status === "RESERVADO"}
      >
        <svg
          className="size-9 p-2"
          xmlns="http://www.w3.org/2000/svg"
          id="Layer_1"
          data-name="Layer 1"
          viewBox="0 0 24 24"
        >
          <path d="m2,8v-1c0-3.314,2.686-6,6-6h8c3.314,0,6,2.686,6,6v1c-2.209,0-4,1.791-4,4v3H6v-3c0-2.209-1.791-4-4-4Zm19.664,2.027c-.983.16-1.664,1.083-1.664,2.08v3.893c0,.552-.448,1-1,1H5c-.552,0-1-.448-1-1v-3.893c0-.996-.681-1.92-1.664-2.08-1.253-.204-2.336.758-2.336,1.973v4c0,1.636.786,3.088,2,4v2c0,.552.448,1,1,1s1-.448,1-1v-1.1c.323.066.658.1,1,.1h14c.342,0,.677-.034,1-.1v1.1c0,.552.448,1,1,1s1-.448,1-1v-2c1.214-.912,2-2.364,2-4v-4c0-1.215-1.083-2.176-2.336-1.973Z" />
        </svg>
      </button>
      <span className="text-slate-200/50">{id}</span>
    </div>
  );
}
export default SeatButton;
