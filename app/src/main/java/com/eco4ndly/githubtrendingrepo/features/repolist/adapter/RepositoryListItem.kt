package com.eco4ndly.githubtrendingrepo.features.repolist.adapter

import androidx.recyclerview.widget.DiffUtil
import coil.api.load
import com.eco4ndly.githubtrendingrepo.R
import com.eco4ndly.githubtrendingrepo.common.extensions.safeOffer
import com.eco4ndly.githubtrendingrepo.features.repolist.adapter.RepositoryListItem.Event.ItemClicked
import com.eco4ndly.githubtrendingrepo.features.repolist.adapter.RepositoryListItem.RepoItem
import com.eco4ndly.githubtrendingrepo.features.repolist.model.RepoUiModel
import com.eco4ndly.githubtrendingrepo.widgets.ItemAdapter
import com.eco4ndly.githubtrendingrepo.widgets.ItemViewHolder
import kotlinx.android.synthetic.main.layout_repo_item.view.iv_repo_avatar
import kotlinx.android.synthetic.main.layout_repo_item.view.tv_fork_count
import kotlinx.android.synthetic.main.layout_repo_item.view.tv_lang_name
import kotlinx.android.synthetic.main.layout_repo_item.view.tv_repo_description
import kotlinx.android.synthetic.main.layout_repo_item.view.tv_repo_name
import kotlinx.android.synthetic.main.layout_repo_item.view.tv_star_count
import kotlinx.coroutines.channels.SendChannel

/**
 * A Sayan Porya code on 26/05/20
 */
sealed class RepositoryListItem: ItemAdapter.Item<RepositoryListItem.Event> {

  companion object {
    /**
     * Produces list of [RepositoryListItem] from [RepoUiModel]
     */
    fun from(repoUiModelList: List<RepoUiModel>): List<RepositoryListItem> {
      return repoUiModelList
        .map(::RepoItem)
    }
  }

  /**
   * Each item in the [RepositoryListItem]
   */
  data class RepoItem(val repoUiModel: RepoUiModel): RepositoryListItem() {
    override fun layoutResId(): Int = R.layout.layout_repo_item

    override fun render(holder: ItemViewHolder, channel: SendChannel<Event>) {
      with(holder.containerView) {
        repoUiModel.let {
          tv_repo_name.text = it.repoName
          tv_repo_description.text = it.description
          tv_lang_name.text = it.language
          tv_star_count.text = it.starts.toString()
          tv_fork_count.text = it.forks.toString()
          iv_repo_avatar.load(it.avatar) {
            crossfade(true)
            placeholder(R.drawable.ic_image_ph_50dp)
            error(R.drawable.ic_broken_image_50dp)
          }
        }
      }

      holder.itemView.setOnClickListener {
        channel.safeOffer(ItemClicked(repoUiModel))
      }
    }

  }

  /**
   * Events from [RepoListAdapter]
   */
  sealed class Event {
    /**
     * Item click event
     */
    data class ItemClicked(val repoUiModel: RepoUiModel): Event()
  }
}

/**
 * Diff util callback for [RepoListAdapter]
 */
class RepoLisDiffCallback: DiffUtil.ItemCallback<RepositoryListItem>() {
  override fun areItemsTheSame(oldItem: RepositoryListItem, newItem: RepositoryListItem): Boolean {
    return when {
      oldItem is RepoItem && newItem is RepoItem -> oldItem.repoUiModel == newItem.repoUiModel
      else -> false
    }
  }

  override fun areContentsTheSame(
    oldItem: RepositoryListItem,
    newItem: RepositoryListItem
  ): Boolean {
    return oldItem == newItem
  }

}