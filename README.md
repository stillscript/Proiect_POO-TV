# Proiect - README

*Proiectul se gaseste pe [Repo-ul de GitHub](https://github.com/stillscript/Proiect_POO-TV).*

### Functionarea proiectului

1. Partea de input
    - In pachetul *src/classes/fileio* am creat o serie de clase care sa ne ajute sa preluam inputul din *resources/in/\*.json* 
      - Input - campuri *users, movies* si *actions*, retinem un array de **UserInput** cu toti userii, un array de **MoviesInput** pentru filmele initiale si **ActionInput** pentru toate actiunile pe care trebuie sa le executam
        - UserInput - campul *credentials*, **CredentialsInput** in care retinem datele initiale ale utilizatorului
            - CredentialsInput - campuri *name, password, accountType, country* si *balance*
        - MoviesInput - campuri *name, year, duration, genres, countriesBanned* si *actors*, ultimele fiind ArrayList de Stringuri, intrucat puteau contine mai multe valori
        - ActionInput - campuri *type, page, feature, credentials, filters, startsWith, movie, objectType, count* si *rate*
            - **OBSERVATIE** : putem constata ca pentru actiunea de filter, inputul este putin diferit, de aceea are alta constructie, si anume un camp *contains* si unul *sort* => **ContainsInput** are doua ArrayList-uri de Stringuri care retin actorii si genul, iar **SortInput** are 2 Stringuri, unul pentru rating si unul pentru durata
2. Partea de executie a programului
   - Am ales sa folosesc ca design pattern Singleton si Visitor
     - Singleton, intrucat un site poate avea o singura pagina de login, o singura pagina de logout, etc..
     - Visitor, intrucat daca este implementat corect, pot face executia actiunilor mult mai repede, pot adauga si scoate functionalitati mult mai usor, iar introducerea outputului in fisiere este si ea facilitata
   - Pachetul *actions*
     - Am implementat toate actiunile posibile folosind Visitor(interfetele Action si ActionVisitor, clasa ActionVisitorImpl), fiecare dintre celelalte clase fiind pentru a putea construi ArrayList-ul de actiuni
   - Pachetul *pages*
     - Am implementat paginile prin Singleton, intrucat un site poate avea doar o pagina de login, logout, register, etc..
   - Movie
     - Reprezinta o constructie propriu-zisa a unui film pe baza inputului preluat din fisiere; contine toate campurile din *MovieInput*, iar in plus sunt *numLikes, rating, numRating* ce trebuie sa apara la Output, dar si un camp ascuns, numit *allRatings* in care se retin toate rating-urile primite de un film, pentru a-i putea calcula rating-ul mediu la un moment dat mai usor -> ex.: un film cu 3 evaluari(2,3,4) va avea numRating = 3 si allRatings = {2,3,4}, adunam toate elementele Array-ului si le impartim la 3, obtinand rating-ul mediu = 3.
   - Output
     - Are o constructie standard, indiferent de situatie: *error, currentMoviesList, currentUser*
   - Site
     - Retinem lista de useri, filme, actiuni, toate paginile, pagina curenta si utilizatorul curent
     - In constructor, adaugam toti userii, filmele si actiunile din input, construim paginile si setam care sunt paginile in care putem ajunge -> ex.: din homepage neautentificat putem ajunge doar in homepage neautentificat, login si register
       - Structura site-ului este urmatoarea
       ![Structura](https://i.imgur.com/UYA4Jgs.png)
     - Ultima functie, *exec*, va executa toate actiunile din lista de actiuni, iar in cazul in care aceste actiuni au un output, acesta va fi redirectionat catre fisierul *output.txt*, unde este verificat de checker.
   - User
     - Pe langa *credentials*, retinem numarul de token, de filme gratis daca este utilizator premium, filme cumparate/vizionate/pe care le apreciaza/carora le-a dat rating, dar si inca un camp ascuns, *allowedMovies* reprezentand o lista cu filmele la care utilizatorul are acces: initial, aceasta lista este reprezentata de filmele la care are voie sa se uite dintr-o anumita tara, mai apoi se schimba in functie de filtru
3. Flow-ul programului
    - Programul se executa prin rularea fisierului Test, acesta apeleaza functia Main 
    - Main deschide fisierul de input, preia datele, creeaza site-ul, pregateste output-ul si apoi ruleaza functia *exec*
    - *exec* creeaza visitorul, ia fiecare actiune din lista si o ruleaza, retine intr-un String tot outputul, iar mai apoi il adauga in fisierul specificat
    - Dupa ce se creeaza fisierul de output, acesta este verificat de Test sa coincida cu fisierele din 'checker/resources/ref'