/*CREATE VIEW books_by_author AS
SELECT book.id,
       book.uuid,
       book.title,
       book.publication_year,
       book.category_id,
       author.id,
       author.uuid,
       author.firstname,
       author.lastname
FROM public.book book
         JOIN public.author author ON book.author_id = author.id;

CREATE VIEW vinyls_by_artist AS
SELECT
vinyl.id,
       vinyl.uuid,
       vinyl.title,
       vinyl.publication_year,
       vinyl.category_id,
       artist.id,
       artist.uuid,
       artist.name
FROM public.vinyl vinyl
         JOIN public.artist artist ON vinyl.artist_id = artist.id;

*/