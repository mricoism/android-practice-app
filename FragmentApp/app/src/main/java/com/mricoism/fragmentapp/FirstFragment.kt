package com.mricoism.fragmentapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mricoism.fragmentapp.databinding.ActivityMainBinding
import com.mricoism.fragmentapp.databinding.FragmentFirstBinding


// maybe you need to see this https://stackoverflow.com/questions/34706399/how-to-use-data-binding-with-fragment
/*
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFirstFragment.text = "Hello"
    }

}

 */


class FirstFragment: Fragment(R.layout.fragment_first) {


}


/*
Fragment classes also have tons of functions that you can overwrite just as activities have.
you can see, one function that you will see very often. Which is onCreateView.
That function purpose is to inflate our view of that fragment so it will take our layout file fragment_first and we turn it as a view. so that this fragment knows which view it should show.
And i told you that fragments have their own lifecycle. So there is also an onCreate function just as for activities.
How ever the lifecycle fragments is slightly different but they still have an onCreate function that is called initially when that fragment is created.

we can't treat fragments onCreates function like an activities on create function.
in an activity class we set the content view in onCreate, but fragments don't do that because they have this onCreateView function which create that view.
And the onCreateView function is called after onCreate. So we cannot access the views of fragments inside of onCreate function. like we could do it for activities.
So in fragments, the views is actually created after onCreate is called.
And if you want to access the fragment views, then you need to override onViewCreated. This function is called when all the views are created.
And here you are fine to access tvFirstFragment.

 */