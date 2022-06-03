ALTER TABLE public.pump
DROP CONSTRAINT location_pump_fk;
ALTER TABLE public.pump
    DROP COLUMN location;