INSERT INTO "projects" (id,
                        number,
                        name,
                        condition,
                        average_completion_percentage,
                        director)
VALUES (generate_series(1, 10000),
        substr('N ' || gen_random_uuid()::text, 1, 7),
        substr('Project ' || gen_random_uuid()::text, 1, 14),
        (array['FORMING', 'APPROVING', 'REFINEMENT', 'IMPLEMENTATION'])[floor(random() * 4 + 1)],
        0,
        substr('Director ' || gen_random_uuid()::text, 1, 12)
       );

INSERT INTO "works" (id,
                     name,
                     start_date,
                     finish_date,
                     average_completion_percentage,
                     implementer,
                     project_id,
                     checkpoint_id)
VALUES (generate_series(1, 100000),
        substr('Work ' || gen_random_uuid()::text, 1, 12),
        '2022-08-30 10:10:10',
        '2023-08-30 10:10:10',
        floor(random() * 100),
        substr('Implementer ' || gen_random_uuid()::text, 1, 15),
        floor(random() * 10000+ 1),
        null
       );