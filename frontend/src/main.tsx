import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.tsx";

createRoot(document.getElementById("root")!).render(
  <div className="w-full bg-slate-800 h-dvh flex flex-col items-center justify-center">
    <App />
  </div>,
);
