SELECT	lu.SYS_I AS LineUP_SYS_ID,
		DATEDIFF(dd, '29-MAR-2005', LU.UN_CLIP_T) AS DateDiff, 
		cp.CNCT_I,
		lu.TOT_LTH_N,
		lu.CLIP_CNT_N,
		lu.HOSE_CNT_N,
		cn.CNCT_PT_FROM_SYS_I,
		cn.CNCT_PT_TO_SYS_I,
		lu.RESV_T, 
		lu.UN_CLIP_T
FROM	MNFLD.dbo.wmgma04_ln_up AS lu, 
		MNFLD.DBO.wmgma01_cnct_pt AS cp, 
		MNFLD.dbo.WMGMA05_CNCT AS cn
WHERE	lu.SYS_I > 200 AND
		cp.SYS_I = lu.CNCT_PT_TO_SYS_I AND 
		cp.SYS_I < 200 AND
		lu.SYS_I = cn.LN_UP_SYS_I
ORDER BY	DateDiff ASC, 
			lu.TOT_LTH_N ASC,
			lu.HOSE_CNT_N ASC,
			lu.CLIP_CNT_N ASC,
			lu.SYS_I