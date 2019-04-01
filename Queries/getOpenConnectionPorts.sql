-- Retrieves all the Non-Active Ports and Sorts them by number of reservations
SELECT *
FROM MNFLD.dbo.wmgma01_cnct_pt AS cp 
WHERE cp.ACTV_X =0 AND cp.RESV_IN_USE_N < 2
ORDER BY cp.RESV_IN_USE_N ASC, cp.CNCT_I