package mr.booroondook.fragments_and_actionbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class MainFragment extends LoggingFragment {
    private String[] lastNames;
    private ListView listView;

    private int ic_check_id_icon;
    private int adopted_id_string;
    private int ic_close_id_icon;
    private int fired_id_string;

    private CustomAdapter customAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        lastNames = getResources().getStringArray(R.array.last_names);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        listView = view.findViewById(R.id.list_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (customAdapter == null) {
            customAdapter = new CustomAdapter();
            updateAdapter();
        }
        listView.setAdapter(customAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset:
                updateAdapter();
                listView.setAdapter(customAdapter);
                return true;
            case R.id.check:
                ic_check_id_icon = R.drawable.ic_check_24dp;
                adopted_id_string = R.string.adopted;
                ic_close_id_icon = R.drawable.ic_check_24dp;
                fired_id_string = R.string.adopted;
                listView.setAdapter(new CustomAdapter());
                return true;
            case R.id.adopted:
                ic_check_id_icon = R.drawable.ic_close_24dp;
                adopted_id_string = R.string.fired;
                ic_close_id_icon = R.drawable.ic_close_24dp;
                fired_id_string = R.string.fired;
                listView.setAdapter(new CustomAdapter());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateAdapter() {
        ic_check_id_icon = R.drawable.ic_check_24dp;
        adopted_id_string = R.string.adopted;
        ic_close_id_icon = R.drawable.ic_close_24dp;
        fired_id_string = R.string.fired;
    }

    private class CustomAdapter extends ArrayAdapter<String> {
        CustomAdapter() {
            super(Objects.requireNonNull(MainFragment.this.getActivity()),
                    R.layout.list_cell, R.id.unit_name, lastNames);
        }

        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            ViewHolder viewHolder = (ViewHolder) view.getTag();

            if (viewHolder == null) {
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }

            if (Objects.requireNonNull(((CustomAdapter) listView.getAdapter())
                    .getItem(position)).length() % 2 == 0) {
                viewHolder.getIconStatus().setImageResource(ic_check_id_icon);
                viewHolder.getUnitStatus().setText(adopted_id_string );
            } else {
                viewHolder.getIconStatus().setImageResource(ic_close_id_icon );
                viewHolder.getUnitStatus().setText(fired_id_string );
            }
            return view;
        }
    }

    private static class ViewHolder {
        private final ImageView iconStatus;
        private final TextView unitStatus;

        ViewHolder(View view) {
            this.iconStatus = view.findViewById(R.id.icon_status);
            this.unitStatus = view.findViewById(R.id.unit_status);
        }

        ImageView getIconStatus() {
            return iconStatus;
        }

        TextView getUnitStatus() {
            return unitStatus;
        }
    }
}
