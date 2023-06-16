# readTxt

ReadTxt - apllikacja napisana w języku Java 17 z użyciem frameworka Spring Boot 3.1. Aplikacja udostępnia REST API
endpoint read obsługujący metodę Http POST, który przyjmuje plik tekstowy. Aplikacja zwraca w formacie json ranking powtarzających się słów.
Zwracana lista jest posortowana malejąco po liczbie wystąpień.
Aplikacja działa na porcie 8090.

## Instalacja i uruchomienie

W katalogu znajduje się skrypt bashowy który po uruchomieniu buduje aplikację oraz ją uruchamia.
Wymagane aby na uruchamianym systemie był zainstalowany Maven oraz JDK w wersji 17.

Dla systemu linux:
```bash
./startup.sh
```
Dla systemu Windows
```bash
startup.bat
```
## Przykład użycia

Korzystając z klienta Http można wysłać zapytanie. W katalogu resource znajduje się przykładowy plik txt. Przykład:
```bash
curl localhost:8090/read -F file=@test.txt
``` 
