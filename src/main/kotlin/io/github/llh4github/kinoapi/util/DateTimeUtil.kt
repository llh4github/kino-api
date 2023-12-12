package io.github.llh4github.kinoapi.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 *
 * Created At 2023/12/10 16:07
 * @author llh
 */
object DateTimeUtil {
    fun toDate(
        dateTime: LocalDateTime,
        zoneId: ZoneId = ZoneId.systemDefault()
    ): Date {
        return Date.from(dateTime.atZone(zoneId).toInstant())
    }
}