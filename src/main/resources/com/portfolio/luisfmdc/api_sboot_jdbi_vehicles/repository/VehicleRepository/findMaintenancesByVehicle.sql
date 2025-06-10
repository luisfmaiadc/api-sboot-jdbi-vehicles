SELECT id,
    id_veiculo AS idVeiculo,
    id_oficina AS idOficina,
    descricao,
    custo,
    data AS dataManutencao,
    ativa
FROM TbManutencao
WHERE id_veiculo = :vehicleId