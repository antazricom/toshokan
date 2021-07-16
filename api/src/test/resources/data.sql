INSERT INTO public.category
    (id, name, uuid)
VALUES
    (1, 'Littérature japonaise', 'dcf4fa13-c420-4060-bf8f-44d95f046a65'),
    (2, 'Littérature française', '0d3bed1c-641d-4893-8bf9-f9a2645cf2f6'),
    (3, 'Littérature russe', '6d761fd5-4298-4d91-bf6e-00e694f69381'),
    (4, 'Littérature américaine', '36478908-a36b-4879-aebe-673a7a4a3fe9'),
    (5, 'Fantastique - Fantasy', 'e60ef8ea-695b-4eb6-8793-9f04cacec1e2');

ALTER SEQUENCE category_id_seq RESTART 6;

INSERT INTO public.author
    (id, firstname, lastname, uuid)
VALUES
    (1, 'Haruki', 'Murakami', '1555717d-a80e-4c50-ab30-3ee401628eb5'),
    (2, 'Risa', 'Wataya', 'ec8278e5-ccdf-4079-b706-439e2d111c43'),
    (3, 'Ernest', 'Hemingway', 'c06513e2-b7b6-41bd-9f65-a59e4088d033'),
    (4, 'Leon', 'Tolstoï', '21a37923-a8b8-4a6b-b05d-8bdccc8b3ccc'),
    (5, 'John Ronald Reuel', 'Tolkien', '5e5f1181-2a10-4e96-9b73-ce4c7ba2b978');

ALTER SEQUENCE author_id_seq RESTART 6;

INSERT INTO public.book
    (id, isbn, publication_year, synopsis, title, uuid, author_id, category_id)
VALUES
    (1, '2714447074', '2009', 'Synopsis 1', '1Q84 Tome 1', '9cef070b-ccdf-44ce-a355-b68eb509d946', 1, 1),
    (2, '2714449840', '2009', 'Synopsis 2', '1Q84 Tome 2', '5f4d67ee-5274-44fa-98b3-e7f12a0f75d3', 1, 1),
    (3, '2714449859', '2010', 'Synopsis 3', '1Q84 Tome 3', 'c75a4ae6-f797-4536-868f-a19b10912c3c', 1, 1),
    (4, '2253049417', '1937', 'Synopsis 4', 'Le Hobbit', 'e0116867-ee11-4ca0-8909-542aac74e5b2', 5, 5),
    (5, '2266154117', '1954', 'Synopsis 5', 'Le Seigneur des Anneaux, Tome 1 : La Communauté de l''Anneau', '8b9d3896-cb72-49f3-940a-82d6e3a2a4cd', 5, 5),
    (6, '2070364550', '1940', 'Synopsis 6', 'Pour qui sonne le glas', '2dac057a-08cd-45dc-b818-7fa09a2f5d81', 3, 4),
    (7, '207036027X', '1929', 'Synopsis 7', 'L''adieu aux armes', '72dcfbfd-2a22-4064-8c69-fe56a20edc93', 3, 4),
    (8, '2809700168', '2008', 'Synopsis 8', 'Appel du pied', '996f1c08-0fa0-4892-a6f7-28dd553ff4df', 2, 1);

ALTER SEQUENCE book_id_seq RESTART 9;

INSERT INTO public.note
    (id, content, uuid, book_id)
VALUES
    (1, 'Ma nouvelle note', '60a9dfca-3c20-4400-bd13-0e2dc5d22ee1', 1),
    (2, 'Ma nouvelle note 2', '6968e3a2-31a7-41e3-850f-e0894a97c268', 3),
    (3, 'Ma nouvelle note 3', 'b03a64a4-75d5-4c16-b23d-9259f42015d8', 5),
    (4, 'Ma nouvelle note 4', '1e5db93e-d8cb-4d9d-a841-f87ff36a6140', 7);
ALTER SEQUENCE note_id_seq RESTART 6;