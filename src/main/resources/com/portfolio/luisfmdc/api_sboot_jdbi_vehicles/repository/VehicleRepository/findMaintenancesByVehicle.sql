SELECT id,
    id_veiculo AS idVeiculo,
    descricao,
    custo,
    data AS dataManutencao,
    ativa
FROM TbManutencao
WHERE id_veiculo = :vehicleId