
select rownum, num from kicboard
where rownum between 1 and 3
order by num desc;



select rownum, num from kicboard
where rownum between 4 and 6
order by num desc;



select rownum, num from kicboard
where rownum >= 3
order by num desc;


select * from (
select rownum rnum, a.* from (
select * from kicboard 
where boardid ='1'
order by num desc) a)
where rnum between 7 and 9;