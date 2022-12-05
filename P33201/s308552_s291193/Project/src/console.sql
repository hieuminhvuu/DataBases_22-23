call sponsor_new_club(1,1,4000);
select get_total_amount_from_sponsors(0);
select update_amount(0,1000);
insert into league values (1,'Champion League','Europe','CLUB');
insert into match_league values (0,1);
call tranfer_player(0,1);
select get_total_amount_from_sponsors(1) ;
