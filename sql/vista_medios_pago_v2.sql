CREATE VIEW vista_medios_pago_v2
AS 
SELECT 
factura.id,
factura.fecha,
factura.numero,
fac_pagos.moneda,
fac_pagos.monto,
fac_pagos.vuelto,
fac_pagos.total,
fac_pagos.referencia,
fac_pagos.idbanco,
fac_pagos.fecpago,
fac_pagos.horpago,
fac_pagos.tasacambio,
bancos.codigo,
bancos.descrip
FROM fac_pagos 
LEFT JOIN factura ON fac_pagos.idfactura = factura.id 
LEFT JOIN bancos  ON fac_pagos.idbanco = bancos.id
WHERE pagada=1
