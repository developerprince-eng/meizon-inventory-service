ALTER TABLE public.tank
DROP CONSTRAINT tank_meta_fk;
ALTER TABLE public.tank
DROP COLUMN meta;
ALTER TABLE public.tank
ADD COLUMN referenced_height VARCHAR(16);
