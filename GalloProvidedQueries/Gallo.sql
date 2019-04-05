select lu.SYS_I as [Line Up]
       , cn.SEQ_N as [Seq]
       , cp1.[CNCT_I] as [From Port]
       , ct1.[CNCT_PT_TYP_D] as [From Type]
       , cp2.[CNCT_I] as [To Port]
       , ct2.[CNCT_PT_TYP_D] as [To Type]
       , lu.[CNCT_CNT_N] as [Connect Cnt]
       , lu.TOT_LTH_N as [Connect Len]
       , lu.HOSE_CNT_N as [Hose Cnt]
       , lu.CLIP_CNT_N as [Clip Cnt]
from [MNFLD].[DBO].[wmgma04_ln_up] as lu
join [MNFLD].[DBO].[wmgma01_cnct_pt] cp1 on lu.CNCT_PT_FROM_SYS_I = cp1.SYS_I
join [MNFLD].[DBO].[wmgma01_cnct_pt] cp2 on lu.CNCT_PT_TO_SYS_I = cp2.SYS_I
join [MNFLD].[DBO].[WMGMA10_CNCT_PT_TYP] ct1 on cp1.CNCT_PT_TYP_SYS_I = ct1.[SYS_I]
join [MNFLD].[DBO].[WMGMA10_CNCT_PT_TYP] ct2 on cp2.CNCT_PT_TYP_SYS_I = ct2.[SYS_I]
join [MNFLD].[DBO].[WMGMA05_CNCT] cn on lu.SYS_I = cn.LN_UP_SYS_I
where cp1.SITE_SYS_I = 10
order by lu.SYS_I, cn.SEQ_N

select distinct lu.sys_i as 'Line Up Id'
       , cn.SEQ_N as [Seq]
       , cp1.[CNCT_I] as [From Port]
       , cp2.[CNCT_I] as [To Port]
       , lu.[CNCT_CNT_N] as [Connect Cnt]
       , lu.HOSE_CNT_N as [Hose Cnt]
       , lu.CLIP_CNT_N as [Clip Cnt]
from [MNFLD].[DBO].[WMGMA05_CNCT] cn
join [MNFLD].[DBO].[wmgma01_cnct_pt] cp1 on cn.CNCT_PT_FROM_SYS_I = cp1.SYS_I
join [MNFLD].[DBO].[wmgma01_cnct_pt] cp2 on cn.CNCT_PT_TO_SYS_I = cp2.SYS_I
join [MNFLD].[DBO].[wmgma04_ln_up] lu on cn.LN_UP_SYS_I = lu.SYS_I
order by lu.SYS_I, cn.SEQ_N

select cp1.CNCT_I as Side1
       ,cp2.CNCT_I as Side2
       ,p.LTH_N
from [MNFLD].[DBO].[WMGMA08_PIPE] p
join [MNFLD].[DBO].[wmgma01_cnct_pt] cp1 on p.CNCT_PT_SIDE1_SYS_I = cp1.SYS_I
join [MNFLD].[DBO].[wmgma01_cnct_pt] cp2 on p.CNCT_PT_SIDE2_SYS_I = cp2.SYS_I
where cp1.SITE_SYS_I = 10
order by cp1.CNCT_I, cp2.CNCT_I

select distinct left(cp1.cnct_i,3)
from [MNFLD].[DBO].[WMGMA08_PIPE] p
join [MNFLD].[DBO].[wmgma01_cnct_pt] cp1 on p.CNCT_PT_SIDE1_SYS_I = cp1.SYS_I
join [MNFLD].[DBO].[wmgma01_cnct_pt] cp2 on p.CNCT_PT_SIDE2_SYS_I = cp2.SYS_I
where cp1.SITE_SYS_I = 10
order by left(cp1.cnct_i,3)

select 'Panel' as 'Connection'
       ,left(cp1.cnct_i,3) as 'Panel S1'
       ,cp1.cnct_i as 'Side 1'
       ,ct1.[CNCT_PT_TYP_D] as 'Connect 1 Type'
       ,left(cp2.cnct_i,3) as 'Panel S2'
       ,cp2.cnct_i as 'Side 2'
       ,ct2.[CNCT_PT_TYP_D] as 'Connect 2 Type'
       ,Case When pc.CLIP_SZ = 0 Then pc.HOSE_CNT_N * 25.0
               When pc.HOSE_CNT_N = 0 Then pc.CLIP_SZ/12.0
       Else 0
       End as 'Length'
       ,pc.clip_sz as 'Clip Size'
       ,pc.hose_cnt_n as 'Hose Count'
from [MNFLD].[DBO].[WMGMA02_POSBL_CNCT] pc
join [MNFLD].[DBO].[WMGMA01_CNCT_PT] cp1 on pc.CNCT_PT_SIDE1_SYS_I = cp1.SYS_I
join [MNFLD].[DBO].[WMGMA01_CNCT_PT] cp2 on pc.[CNCT_PT_SIDE2_SYS_I] = cp2.SYS_I
join [MNFLD].[DBO].[WMGMA10_CNCT_PT_TYP] ct1 on cp1.CNCT_PT_TYP_SYS_I = ct1.SYS_I
join [MNFLD].[DBO].[WMGMA10_CNCT_PT_TYP] ct2 on cp2.CNCT_PT_TYP_SYS_I = ct2.SYS_I
and cp1.SITE_SYS_I = 10
and cp2.SITE_SYS_I = 10
and cp1.cnct_i not in ('PRODUCT', 'FINWINE')
and cp2.cnct_i not in ('PRODUCT', 'FINWINE')
--order by cp1.cnct_i, cp2.cnct_i

Union

select 'Pipe' as 'Connection'
       ,left(cp1.cnct_i,3) as 'Panel S1'
       ,cp1.cnct_i as 'Side 1'
       ,'PORT' as 'Connect 1 Type'
       ,left(cp2.cnct_i,3) as 'Panel S2'
       ,cp2.cnct_i as 'Side 2'
       ,'PORT' as 'Connect 2 Type'
       ,p.LTH_N as 'Length'
       ,0 as 'Clip Size'
       ,0 as 'Hose Count'
from [MNFLD].[DBO].[WMGMA08_PIPE] p
join [MNFLD].[DBO].[WMGMA01_CNCT_PT] cp1 on p.CNCT_PT_SIDE1_SYS_I = cp1.SYS_I
join [MNFLD].[DBO].[WMGMA01_CNCT_PT] cp2 on p.[CNCT_PT_SIDE2_SYS_I] = cp2.SYS_I
where cp1.SITE_SYS_I = 10
and cp2.SITE_SYS_I = 10
order by cp1.cnct_i, cp2.cnct_i
