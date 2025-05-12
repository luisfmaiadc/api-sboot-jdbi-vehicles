SELECT EXISTS (
    SELECT 1
    FROM TbManutencao
    WHERE id_veiculo = :vehicleId AND ativa = true
)