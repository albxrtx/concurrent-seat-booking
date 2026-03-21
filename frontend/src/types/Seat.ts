export type SeatStatus = "LIBRE" | "RESERVADO" | "PENDIENTE";

export type Seat = {
  id: string;
  status: SeatStatus;
};
