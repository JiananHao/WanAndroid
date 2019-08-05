package com.example.wanandroid.di.Component;

import com.example.wanandroid.di.Module.FragmentModule;
import com.example.wanandroid.ui.fragment.first.child.HomeFragment;
import com.example.wanandroid.ui.fragment.fourth.child.ItemFragment;
import com.example.wanandroid.ui.fragment.fourth.child.ProjectFragment;
import com.example.wanandroid.ui.fragment.second.child.KnowledgeFragment;
import com.example.wanandroid.ui.fragment.third.child.PlayFragment;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(HomeFragment homeFragment);

    void inject(KnowledgeFragment knowledgeFragment);

    void inject(PlayFragment playFragment);

    void inject(ProjectFragment projectFragment);

    void inject(ItemFragment itemFragment);
}
