SELECT
    routine_schema,
    routine_name
FROM
    information_schema.routines
WHERE
        routine_type = 'PROCEDURE';


 create or replace procedure edit_merchant_status_byName(merchant_name varchar, isOpen bool)
 language plpgsql
 as $$
 begin
	 update merchant set is_open = isOpen where name = merchant_name;
 	commit ;
 end;$$


select * from merchant m where is_open

