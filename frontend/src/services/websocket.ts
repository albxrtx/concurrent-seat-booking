import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import type { Seat } from "../types/Seat";

let stompClient: Client | null = null;

export const connectWebSocket = (onMessageReceived: (seat: Seat) => void) => {
  const socket = new SockJS("http://localhost:8080/ws");

  stompClient = new Client({
    webSocketFactory: () => socket,
    debug: (str) => console.log(str),
  });

  stompClient.onConnect = () => {
    console.log("WebSocket conectado");

    stompClient?.subscribe("/topic/seats", (message) => {
      const updatedSeat: Seat = JSON.parse(message.body);

      onMessageReceived(updatedSeat);
    });
  };

  stompClient.activate();
};

export const disconnectWebSocket = () => {
  if (stompClient) {
    stompClient.deactivate();
    console.log("WebSocket desconectado");
  }
};
