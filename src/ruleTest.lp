1:bird(X) :- residentbird(X).
bird(X) :- migratorybird(X).
:- residentbird(X), migratorybird(X).
residentbird(jo).
migratorybird(jo).