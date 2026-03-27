import requests
from concurrent.futures import ThreadPoolExecutor, as_completed
import threading
import time
from colorama import Fore, Style

URL = "http://localhost:8080/seats/A1"

start_event = threading.Event()
print("----- PRUEBA RACE CONDITION CON OPTIMISTIC LOCKING -----")


def make_request(i):
    try:
        start_event.wait()
        response = requests.post(URL, timeout=5)
        if response.status_code != 409:
            return (
                Fore.GREEN + f"Request {i} -> {response.status_code}" + Style.RESET_ALL
            )
        else:
            return Fore.RED + f"Request {i} -> {response.status_code}" + Style.RESET_ALL
        # return f"Request {i} -> {response.status_code}"
    except Exception as e:
        return f"Request {i} -> ERROR: {e}"


futures = []

with ThreadPoolExecutor(max_workers=20) as executor:
    for i in range(20):
        futures.append(executor.submit(make_request, i))

    # liberar todos a la vez
    start_event.set()

    # recoger resultados
    for future in as_completed(futures):
        print(future.result())
