alter table consult add cancel tinyint;
update consult set cancel = false;
alter table consult add motive VARCHAR(100);
