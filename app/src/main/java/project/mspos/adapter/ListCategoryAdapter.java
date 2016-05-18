package project.mspos.adapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import project.mspos.R;
import project.mspos.utils.Const;


public class ListCategoryAdapter extends ExpandableRecyclerAdapter<ListCategoryAdapter.CategoriesListItem> {
        public static final int TYPE_SUB_CATEGORY = 1001;
        public ListCategoryAdapter(Context context) {
            super(context);
        }

        public static class CategoriesListItem extends ExpandableRecyclerAdapter.ListItem {
            public String Text;

            public CategoriesListItem(String group) {
                super(TYPE_HEADER);
                Text = group;
            }

            public CategoriesListItem(String categoryName, String last) {
                super(TYPE_SUB_CATEGORY);
                Text = categoryName;
            }
        }

        public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
            TextView name;

            public HeaderViewHolder(View view) {
                super(view, (ImageView) view.findViewById(R.id.item_arrow));
                name = (TextView) view.findViewById(R.id.item_header_name);
            }

            public void bind(int position) {
                super.bind(position);
                Const.mapCategory = new HashMap<Integer, String>();
                name.setText(visibleItems.get(position).Text);

            }
        }

        public class CategoryViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
            TextView name;

            public CategoryViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.item_name);
            }
            public void bind(int position) {
                name.setText(visibleItems.get(position).Text);
                Const.mapCategory.put(position,visibleItems.get(position).Text);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TYPE_HEADER:
                    return new HeaderViewHolder(inflate(R.layout.item_category_header, parent));
                case TYPE_SUB_CATEGORY:
                default:
                    return new CategoryViewHolder(inflate(R.layout.item_category, parent));
            }
        }

        @Override
        public void onBindViewHolder(ExpandableRecyclerAdapter.ViewHolder holder, int position) {
            switch (getItemViewType(position)) {
                case TYPE_HEADER:
                    ((HeaderViewHolder) holder).bind(position);
                    break;
                case TYPE_SUB_CATEGORY:
                default:
                    ((CategoryViewHolder) holder).bind(position);
                    break;
            }
        }
    }
