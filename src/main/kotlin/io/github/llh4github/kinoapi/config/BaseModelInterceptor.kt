package io.github.llh4github.kinoapi.config

import io.github.llh4github.kinoapi.model.BaseModel
import io.github.llh4github.kinoapi.model.BaseModelDraft
import org.babyfish.jimmer.kt.isLoaded
import org.babyfish.jimmer.sql.DraftInterceptor
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 *
 * Created At 2023/12/3 14:56
 * @author llh
 */
@Component
class BaseModelInterceptor : DraftInterceptor<BaseModelDraft> {
    override fun beforeSave(draft: BaseModelDraft, isNew: Boolean) {
        if (isNew) {
            if (!isLoaded(draft, BaseModel::createdTime)) {
                draft.createdTime = LocalDateTime.now()
            }
        }
        if (!isNew) {
            if (!isLoaded(draft, BaseModel::updatedTime)) {
                draft.updatedTime = LocalDateTime.now()
            }
        }

    }
}