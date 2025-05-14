package rksp.furniture.repository

import org.springframework.data.jpa.repository.JpaRepository
import rksp.furniture.model.FurnitureItem

interface FurnitureRepository : JpaRepository<FurnitureItem, Long>
