select 'Panel' as 'Connection'
       ,left(cp1.cnct_i,3) as 'Panel S1'
       ,cp1.cnct_i as 'Side 1'
       ,left(cp2.cnct_i,3) as 'Panel S2'
       ,cp2.cnct_i as 'Side 2'
       ,Case When pc.CLIP_SZ = 0 Then pc.HOSE_CNT_N * 25.0
               When pc.HOSE_CNT_N = 0 Then pc.CLIP_SZ/12.0
       Else 0
       End as 'Length'
       ,pc.clip_sz as 'Clip Size'
       ,pc.hose_cnt_n as 'Hose Count'
from [MNFLD].[DBO].[WMGMA02_POSBL_CNCT] pc
join [MNFLD].[DBO].[WMGMA01_CNCT_PT] cp1 on pc.CNCT_PT_SIDE1_SYS_I = cp1.SYS_I
join [MNFLD].[DBO].[WMGMA01_CNCT_PT] cp2 on pc.[CNCT_PT_SIDE2_SYS_I] = cp2.SYS_I
and cp1.SITE_SYS_I = 10
and cp2.SITE_SYS_I = 10
and cp1.cnct_i not in ('PRODUCT', 'FINWINE')
and cp2.cnct_i not in ('PRODUCT', 'FINWINE')
--order by cp1.cnct_i, cp2.cnct_i

Union

select 'Pipe' as 'Connection'
       ,left(cp1.cnct_i,3) as 'Panel S1'
       ,cp1.cnct_i as 'Side 1'
       ,left(cp2.cnct_i,3) as 'Panel S2'
       ,cp2.cnct_i as 'Side 2'
       ,p.LTH_N as 'Length'
       ,0 as 'Clip Size'
       ,0 as 'Hose Count'
from [MNFLD].[DBO].[WMGMA08_PIPE] p
join [MNFLD].[DBO].[WMGMA01_CNCT_PT] cp1 on p.CNCT_PT_SIDE1_SYS_I = cp1.SYS_I
join [MNFLD].[DBO].[WMGMA01_CNCT_PT] cp2 on p.[CNCT_PT_SIDE2_SYS_I] = cp2.SYS_I
where cp1.SITE_SYS_I = 10
and cp2.SITE_SYS_I = 10
order by cp1.cnct_i, cp2.cnct_i