-- Returns list of possible connections that aren't activated, sorted my minimal hoses then minimal clips since in preferance  hoses << clips
SELECT *
FROM MNFLD.dbo.wmgma02_posbl_cnct AS pc, (SELECT cp.SYS_I FROM MNFLD.dbo.wmgma01_cnct_pt AS cp WHERE cp.ACTV_X = 0 AND cp.RESV_IN_USE_N < 2) AS oc
WHERE pc.ACTV_X = 0 AND (oc.SYS_I = pc.CNCT_PT_SIDE1_SYS_I OR oc.SYS_I = pc.CNCT_PT_SIDE2_SYS_I)
ORDER BY HOSE_CNT_N, CLIP_SZ--SYS_I