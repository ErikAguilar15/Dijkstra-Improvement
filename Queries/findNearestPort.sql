-- Returns FROM & TO  Pannels and Ports for Non-Active Ports sorts byb Pannels numbers adn then ports of from 

SELECT 
		-- Separates froma and Pannel
		SUBSTRING(CP.CNCT_I, 1,3) AS FROM_PANNEL,
		SUBSTRING(CP.CNCT_I, 5, 3) AS FROM_PORT,
		SUBSTRING(CP2.CNCT_I, 1,3) AS TO_PANNEL,
		SUBSTRING(CP2.CNCT_I, 5, 3) AS TO_PORT,
		'MAX' AS CLIP_COST--,
		--CP.CNCT_I AS CP_FROM,
		--CP2.CNCT_I AS CP_TO--,
		--DISTINCT(SYS_I),*
FROM MNFLD.dbo.wmgma01_cnct_pt AS CP,
		MNFLD.dbo.wmgma01_cnct_pt AS CP2,
		MNFLD.DBO.WMGMA05_CNCT AS C
WHERE 
	CP.ACTV_X = 0 
	AND CP.RESV_IN_USE_N < 2
	AND C.CNCT_PT_FROM_SYS_I = CP.SYS_I
	AND C.CNCT_PT_TO_SYS_I = CP2.SYS_I
	-- Most likely case were using a Hose
	--AND SUBSTRING(CP.CNCT_I, 4,1) <> '-'
	--OR SUBSTRING(CP2.CNCT_I, 4,1) <> '-'
	-- Most likely case were using a Clip
	AND SUBSTRING(CP.CNCT_I, 4,1) = '-'
	AND SUBSTRING(CP2.CNCT_I, 4,1) = '-'
ORDER BY FROM_PANNEL ASC,
		FROM_PORT ASC