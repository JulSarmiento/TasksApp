package com.julhdev.pendientes.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Formats a timestamp into a human-readable date string.
 * @param time The timestamp to format.
 * @return A string representing the formatted date.
 */
fun formatDate(time: Long): String {
  val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    .withZone(ZoneId.systemDefault())
  return formatter.format(Instant.ofEpochMilli(time))
}