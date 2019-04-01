/*
 * Table output should be simila to pdf handout
 * Diplay Line Up with Sequence Numbers, Connection Port Identifiers From/To,
 * Incoming and Outgoing Connections: 1 Hose & 2 Clips
*/
SELECT	C.LN_UP_SYS_I AS LINE_UP_ID,
		C.SEQ_N AS SEQ, 
		CPF.CNCT_I AS FROM_CP,
		CPT.CNCT_I AS TO_CP,
		CPF.CNCT_PT_TYP_SYS_I AS CONNECTION,
		--CPT.CNCT_PT_TYP_SYS_I AS OUT_TYPE,
		C.CNCT_PT_FROM_SYS_I AS C_FROM_ID, 
		C.CNCT_PT_TO_SYS_I AS C_TO_ID,
		lu.TOT_LTH_N AS LINE_UP_LEN,
		LU.CLIP_CNT_N AS CLIPS,
		LU.HOSE_CNT_N AS HOSES
FROM	MNFLD.dbo.WMGMA05_CNCT AS C, 
		MNFLD.dbo.wmgma04_ln_up AS LU, 
		MNFLD.dbo.wmgma01_cnct_pt AS CPF, 
		MNFLD.dbo.wmgma01_cnct_pt AS CPT
WHERE --C.LN_UP_SYS_I = 1837
LU.TOT_LTH_N > 2500
AND C.LN_UP_SYS_I = lu.SYS_I
AND CPF.SYS_I = C.CNCT_PT_FROM_SYS_I
AND CPT.SYS_I = C.CNCT_PT_TO_SYS_I
AND LU.HOSE_CNT_N < 100
AND LU.CLIP_CNT_N < 100
--HAVING LU.HOSE_CNT_N < LU.CLIP_CNT_N
ORDER BY LU.TOT_LTH_N DESC, C.LN_UP_SYS_I, C.SEQ_N
