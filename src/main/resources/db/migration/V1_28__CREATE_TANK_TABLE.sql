CREATE TABLE IF NOT EXISTS public.tank (
     tank_id varchar(32),
     sku_umo varchar(16),
     capacity BIGINT,
     product varchar(16),
     location_id varchar(32),
     meta varchar(32),
     CONSTRAINT tank_pkey PRIMARY KEY (tank_id),
     CONSTRAINT tank_product_fk
         FOREIGN KEY (product)
             REFERENCES public.product(product_sku)
             ON DELETE NO ACTION
             ON UPDATE CASCADE,
     CONSTRAINT tank_meta_fk
         FOREIGN KEY (meta)
             REFERENCES public.meta(meta_id)
             ON DELETE CASCADE
             ON UPDATE NO ACTION,
     CONSTRAINT tank_location_fk
         FOREIGN KEY (location_id)
             REFERENCES public.location(location_id)
             ON DELETE NO ACTION
             ON UPDATE CASCADE
);