CREATE TABLE IF NOT EXISTS public.volume (
   volume_id varchar(32),
   transacted_volume BIGINT,
   product varchar(16),
   meta varchar(32),
   CONSTRAINT volume_pkey PRIMARY KEY (volume_id),
   CONSTRAINT volume_product_fk
       FOREIGN KEY (product)
           REFERENCES public.product(product_sku)
           ON DELETE NO ACTION
           ON UPDATE CASCADE,
   CONSTRAINT volume_meta_fk
       FOREIGN KEY (meta)
           REFERENCES public.meta(meta_id)
           ON DELETE CASCADE
           ON UPDATE NO ACTION
);