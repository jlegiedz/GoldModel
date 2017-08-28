### Ważne: to nie jest porada inwestycyjna, to ćwiczenie z programowania

##  Zadanie:
  1.  Pobrać ceny złota notowane przez NBP, obliczyć średni kurs złota, zapisać wyniki w pliku.
    Plik Excela (tam za pomocą formuły zostanie obliczony średni kurs złota) lub CSV (gdzie średni kurs złota musi być wpisany bezpośrednio, obliczony w aplikacji).
    Do pliku powinny trafić zarówno pobrane wartości jaki i uzyskany rezultat. Np. wszystkie notowania w 1. kolumnie, średnia w 2. kolumnie (jedyna wartość), rekomendacja (podpunkt 2. zadania) w 3. kolumnie (znów jedyna wartość) i klauzula (podpunkt 5. zadania) w kolumnie 4. Opcjonalnie dodaj wiersz z nagłówkami.
    Zakres musi obejmować konfigurowalną w czasie wykonania ilość dni liczoną od dnia bieżącego (maksymalny okres to 367 dni).
  2.  Oprócz średniego kursu w obu miejscach (konsola, arkusz, csv) powinna pojawić się rekomendacja, zależnie od tego, czy średnia z całego okresu ($) jest wyższa, równa czy niższa od średniej z ostatnich 3 dni (#):
    Jeśli (#) różni się od ($) o co najwyżej 1%, to rekomendacja brzmi “TRZYMAJ”,
    Jeśli (#) jest Uwaga, to nie jest porada inwestycyjnawyższa od ($), to rekomendacja brzmi “SPRZEDAWAJ”,
    Jeśli (#) jest mniejsza od ($), to rekomendacja brzmi “KUPUJ”.
  3.  Napisać testy jednostkowe. Część rekomendacyjna powinna być wykonana w podejściu TDD.
  4.  Zastanów się, co zrobić, jeśli żądany okres jest krótszy, niż 3 dni - zabronić takiej sytuacji, wyświetlić komunikat, że rekomendacja jest niedostępna, czy coś innego?
  5.  Program we wszystkich miejscach, gdzie widoczna jest rekomendacja, musi wyświetlać klauzulę “.”

    Kroki:
    Zapoznać się z dokumentacją API: http://api.nbp.pl/
    Wygenerować model JSONowy do obsługi odpowiedzi: http://www.jsonschema2pojo.org/
    Pobrać od użytkownika długość okresu (sprawdzenie poprawności!)
    Pobrać dane z serwisu
    Obliczyć statystyki
    Wyświetlić rekomendację
    Zapisać dane w pliku Excela, razem z formułą obliczania średniej lub CSV, obliczając średnią w kodzie programu; rekomendacja może być wpisana bezpośrednio tekstem

