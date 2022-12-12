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
     