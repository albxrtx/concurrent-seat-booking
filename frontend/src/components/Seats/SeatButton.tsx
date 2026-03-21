interface Props {
  id: string;
  status: string;
}
const statusStyles: Record<string, string> = {
  LIBRE: " bg-slate-500  fill-slate-300",
  RESERVADO: "cursor-not-allowed bg-slate-900  fill-slate-300/50",
  PENDIENTE: "bg-violet-200  fill-violet-900 shadow-2xl shadow-violet-200/50",
};

function SeatButton({ id, status }: Props) {
  return (
    <div className="flex flex-col items-center justify-center">
      <button
        onClick={() => console.log("Has pulsado el asiento: ", id)}
        className={`group  flex flex-col gap-1 p-2 rounded shadow ${
          statusStyles[status]
        }`}
        disabled={status === "RESERVADO"}
      >
        <svg
          className="p-1"
          xmlns="http://www.w3.org/2000/svg"
          id="Layer_1"
          data-name="Layer 1"
          viewBox="0 0 24 24"
          width="32"
          height="32"
        >
          <path d="M23,11c0-2.206-1.794-4-4-4v-2c0-2.757-2.243-5-5-5h-4c-2.757,0-5,2.243-5,5v2c-2.206,0-4,1.794-4,4v5.5c0,1.379,1.121,2.5,2.5,2.5h7.5v3H7c-.552,0-1,.447-1,1s.448,1,1,1h10c.552,0,1-.447,1-1s-.448-1-1-1h-4v-3h7.5c1.379,0,2.5-1.121,2.5-2.5v-5.5ZM3.5,14c-.171,0-.338,.018-.5,.051v-3.051c0-1.103,.897-2,2-2v3h14v-3c1.103,0,2,.897,2,2v3.051c-.162-.033-.329-.051-.5-.051H3.5Z" />
        </svg>
      </button>
      <span className="text-slate-200/50">{id}</span>
    </div>
  );
}
export default SeatButton;
