SELECT id,
    id_veiculo AS idVeiculo,
    descricao,
    custo,
    data AS dataManutencao
FROM TbManutencao
WHERE id = :id;