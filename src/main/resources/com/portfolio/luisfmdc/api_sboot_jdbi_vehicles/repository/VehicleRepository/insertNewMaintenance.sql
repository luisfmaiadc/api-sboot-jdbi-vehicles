INSERT INTO TbManutencao (
    id_veiculo,
    id_oficina,
    descricao,
    custo,
    data,
    ativa
) VALUES (
    :idVeiculo,
    :idOficina,
    :descricao,
    :custo,
    :dataManutencao,
    true
);