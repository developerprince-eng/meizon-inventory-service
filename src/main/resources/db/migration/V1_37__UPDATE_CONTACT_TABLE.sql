ALTER TABLE public.contact
    DROP COLUMN type;
ALTER TABLE public.contact
    ADD COLUMN type varchar(32);
ALTER TABLE public.contact
    ADD CONSTRAINT contact_contact_type_fk
        FOREIGN KEY (type)
            REFERENCES public.contact_type(type_id)
            ON DELETE CASCADE
            ON UPDATE NO ACTION;