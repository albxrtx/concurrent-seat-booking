import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";

createRoot(document.getElementById("root")!).render(
  <div className="w-full bg-slate-800 h-dvh flex flex-col items-center justify-center">
    <header className="fixed top-10 bg-slate-900 w-[50%] text-slate-100/80 p-4 rounded-2xl flex items-center justify-between">
      <p>
        Simular compra masiva del asiento{" "}
        <span className="font-semibold text-violet-500">A1</span>
      </p>
      <button
        onClick={() => console.log("Simulando...")}
        className="bg-violet-200 text-violet-900 shadow-2xl shadow-violet-200/50 font-semibold py-2 px-6 rounded-4xl"
      >
        Simular
      </button>
    </header>
    <App />
  </div>,
);
