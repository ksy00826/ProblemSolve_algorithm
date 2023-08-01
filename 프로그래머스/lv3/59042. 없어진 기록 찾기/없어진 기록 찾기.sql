SELECT AO.ANIMAL_ID ANIMAL_ID, AO.NAME NAME
FROM ANIMAL_OUTS AO
WHERE AO.ANIMAL_ID NOT IN 
(
    SELECT AI.ANIMAL_ID
    FROM ANIMAL_INS AI
)
ORDER BY AO.ANIMAL_ID ASC

