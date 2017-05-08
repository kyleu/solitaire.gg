package models.cache

import java.util.UUID

import models.user.User

object UserCache {
  def get(id: UUID) = CacheService.getAs[User](s"user.$id")

  def set(user: User) = {
    CacheService.set(s"user.${user.id}", user)
    user
  }

  def remove(id: UUID) = {
    CacheService.remove(s"user.$id")
  }
}
