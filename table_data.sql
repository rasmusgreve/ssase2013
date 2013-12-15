INSERT INTO student VALUES (1, 'Admin', 'Nilson', 'admin', 'Centauri', '77e05fb19ddc6b52c4902689b662f76fe9f7097e4ea4468ed65716596f6026a1', ']^\]', true, false);
INSERT INTO student VALUES (2, 'Rasmus', 'Greve', 'rasmusgreve', 'Testroad 11', '16cfb7ef2219d09fc91d12303e1eaca0a55b09182a5519cc354527e41050c1fc', '`_^_', false, false);
INSERT INTO student VALUES (3, 'Christian', 'Lyngbye', 'chr', 'Testroad 12', '16cfb7ef2219d09fc91d12303e1eaca0a55b09182a5519cc354527e41050c1fc', '`_^_', false, false);

INSERT INTO relationship VALUES(1,2,3,0,true);
SELECT setval('public.relationship_id_seq', 1, true);

SELECT setval('public.student_id_seq', 3, true);

INSERT INTO hobby VALUES (1, 'Fishing'),(2, 'Geocaching'),(3, 'Cooking'),(4, 'Fencing'),(5, 'Equestrianism'),(6, 'Cosplaying'),(7,'Knitting'),(8,'Pottery'),(9,'Antiquing');

SELECT setval('public.hobby_id_seq', 9, true);