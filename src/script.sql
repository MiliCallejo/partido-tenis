/*Obtener todos los productos del rubro "libreria" creados hoy*/
SELECT p.*
FROM rubro r
JOIN producto p ON r.id_rubro = p.id_rubro
WHERE r.rubro = 'libreria' AND DATE(p.fecha_creacion) = DATE(NOW());

/*Obtener monto total vendido por cliente (mostrar nombre del cliente y monto)*/
SELECT c.nombre, c.apellido, SUM(v.precio_unitario * v.cantidad) AS monto_total
FROM cliente c
JOIN venta v ON c.id_cliente = v.id_cliente
GROUP BY c.id_cliente, c.nombre, c.apellido;

/*Obtener cantidad de ventas por producto*/
SELECT p.codigo, p.nombre, SUM(v.cantidad) AS cantidad_total
FROM producto p
JOIN venta v ON p.codigo = v.codigo_producto
GROUP BY p.codigo, p.nombre;

/*Obtener cantidad de productos diferentes comprados por cliente en el mes actual*/
SELECT c.id_cliente, c.nombre, c.apellido, COUNT(DISTINCT v.codigo_producto) AS productos_comprados
FROM cliente c
JOIN venta v ON c.id_cliente = v.id_cliente
WHERE EXTRACT(MONTH FROM v.fecha) = EXTRACT(MONTH FROM CURRENT_DATE)
GROUP BY c.id_cliente, c.nombre, c.apellido;

/*Obtener ventas que tienen al menos un producto del rubro "bazar*/
SELECT DISTINCT v.id_venta, v.fecha, v.precio_unitario
FROM venta v
JOIN producto p ON v.codigo_producto = p.codigo
JOIN rubro r ON p.id_rubro = r.id_rubro
WHERE r.rubro = 'bazar';

/*Obtener rubros que no tienen ventas en los ultimos 2 meses*/
SELECT r.id_rubro, r.rubro
FROM rubro r
LEFT JOIN producto p ON r.id_rubro = p.id_rubro
LEFT JOIN venta v ON p.codigo = v.codigo_producto
WHERE v.fecha IS NULL OR v.fecha < DATE_SUB(CURRENT_DATE, INTERVAL 2 MONTH)
GROUP BY r.id_rubro, r.rubro;
