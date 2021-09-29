CREATE VIEW vista_pagos_resumen
AS 
SELECT
fecha,
sum(monto) as monto,
codigo,
descrip
FROM vista_medios_pago 
GROUP BY CODIGO