package com.example.completenewsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        createTable();
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);  // Return the actual item
        }

        @Override
        public long getItemId(int position) {
            return position;  // You can return position as itemId
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item, parent, false);
            }

            ImageView imageView = convertView.findViewById(R.id.imageview);
            TextView text1 = convertView.findViewById(R.id.text1);
            TextView text2 = convertView.findViewById(R.id.text2);
            TextView text3 = convertView.findViewById(R.id.text3);
            LinearLayout layitem = convertView.findViewById(R.id.layitem);

            HashMap<String, String> hashMap = arrayList.get(position);

            String cat = hashMap.get("cat");
            String image_url = hashMap.get("image_url");
            String title = hashMap.get("title");
            String des = hashMap.get("des");

            Picasso.get()
                    .load(image_url)  // Use the image_url variable, not the string "image_url"
                    .placeholder(R.drawable.is)  // Placeholder image
                    .error(R.drawable.is)  // Error image if loading fails
                    .centerCrop()
                    .fit()
                    .into(imageView);

            text1.setText(cat);
            text2.setText(title);
            text3.setText(des);

            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            text1.setBackgroundColor(color);

            layitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity2.Description = des;
                    MainActivity2.Title = title;
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    MainActivity2.my_bitmap = bitmap;

                    startActivity(new Intent(MainActivity.this, MainActivity2.class));
                }
            });

            return convertView;
        }
    }

    private void createTable() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("cat", "Tech");
        hashMap.put("image_url", "https://images.prothomalo.com/prothomalo-bangla%2F2024-11-12%2Fn04xv5w1%2FRobocat.png?rect=22%2C0%2C326%2C217&auto=format%2Ccompress&fmt=webp&dpr=1.0&format=webp&w=640");
        hashMap.put("title", "স্বয়ংক্রিয়ভাবে বাণিজ্যিক প্রচারণা চালানোর সুযোগ দেবে ‘রোবোকেট’ সফটওয়্যার");
        hashMap.put("des", "বড় পরিসরে বাণিজ্যিক প্রচারণা চালানোর সুযোগ দিতে নিজেদের তৈরি কৃত্রিম বুদ্ধিমত্তা (এআই) প্রযুক্তিনির্ভর ‘রোবোকেট’ সফটওয়্যার উন্মুক্ত করেছে বাংলাদেশি প্রযুক্তিপ্রতিষ্ঠান এডিএন ডিজিনেট। সফটওয়্যারটি কাজে লাগিয়ে সহজেই যেকোনো প্রতিষ্ঠান নিজেদের বিক্রয়, বিপণন, গ্রাহক পরিষেবা এবং দৈনন্দিন বিভিন্ন কাজ সমন্বয় করতে পারবে।");
        arrayList.add(hashMap); hashMap = new HashMap<>();



        hashMap=new HashMap<>();
        hashMap.put("cat", "Android");
        hashMap.put("image_url", "https://i0.wp.com/techzoom.tv/wp-content/uploads/2024/10/appp.jpg?resize=750%2C375&ssl=1");
        hashMap.put("title", "অ্যাপ ডাউনলোডের সময় যেসব বিষয়ে সতর্ক থাকবেন");
        hashMap.put("des", "১. রিভিউ এবং রেটিং চেক করুন\n" +
                "অ্যাপ ডাউনলোড করার আগে তার রিভিউ এবং রেটিং পরীক্ষা করা উচিত। ব্যবহারকারীরা যে অ্যাপটি ব্যবহার করেছেন, তারা তাদের অভিজ্ঞতা শেয়ার করে। অধিকাংশ পজিটিভ রিভিউ সহ অ্যাপ সাধারণত নিরাপদ হতে পারে, তবে কিছু ক্ষেত্রে জাল রিভিউও থাকতে পারে। তাই রিভিউগুলো ভালো করে পড়ুন এবং যদি সম্ভব হয়, একটি নির্ভরযোগ্য সূত্র থেকে অতিরিক্ত তথ্য খুঁজুন।\n" +
                "\n" +
                "২. ডেভেলপারের পরিচিতি\n" +
                "অ্যাপটির ডেভেলপার সম্পর্কে জানুন। পরিচিত ও বিশ্বস্ত ডেভেলপারের তৈরি অ্যাপ সাধারণত নিরাপদ হয়। নতুন বা অজ্ঞাত ডেভেলপারদের অ্যাপ ডাউনলোড করার সময় সতর্ক থাকা উচিত। ডেভেলপারের ওয়েবসাইটে গিয়ে তাদের পণ্য ও পরিষেবার সম্পর্কে জানতে পারেন।\n" +
                "\n" +
                "৩. অনুমতির বিবরণ\n" +
                "অ্যাপ ইনস্টল করার সময় যে সমস্ত অনুমতি চাওয়া হচ্ছে, সেগুলো পরীক্ষা করুন। অনেক সময় অ্যাপ এমন সব অনুমতি চাইতে পারে, যা তার কার্যকলাপের জন্য প্রয়োজনীয় নয়। যেমন, একটি সাধারণ গেম যদি আপনার যোগাযোগ তালিকা বা অবস্থান জানতে চায়, তাহলে সেটি সন্দেহজনক হতে পারে।\n" +
                "\n" +
                "৪. নিরাপত্তা স্ক্যান\n" +
                "গুগল প্লে এবং অ্যাপ স্টোর সাধারণত নিজেদের নিরাপত্তা স্ক্যানিং ব্যবস্থা রয়েছে, তবে আপনার ডিভাইসের সুরক্ষার জন্য একটি ভালো অ্যান্টিভাইরাস অ্যাপ ব্যবহার করা উচিত। এটি নতুন অ্যাপ ইনস্টল করার পর সেগুলি স্ক্যান করতে সাহায্য করবে এবং বিপজ্জনক ফাইলগুলো শনাক্ত করতে সক্ষম হবে।\n" +
                "\n" +
                "৫. সিকিউরিটি আপডেট\n" +
                "অ্যাপগুলো নিয়মিতভাবে আপডেট করা প্রয়োজন, কারণ আপডেটগুলো সাধারণত সুরক্ষা ত্রুটি এবং বাগ ফিক্স করে। ডাউনলোড করা অ্যাপের জন্য আপডেট চালিয়ে যান এবং নিশ্চিত করুন যে সবকিছু সর্বদা আপডেট রয়েছে।\n" +
                "\n" +
                "৬. ফিশিং ও স্ক্যাম থেকে সাবধান\n" +
                "অনেক সময়, ব্যবহারকারীদের নকল অ্যাপ বা ওয়েবসাইটে নিয়ে যেতে ফিশিং প্রচেষ্টা হতে পারে। এ ধরনের সাইটগুলোতে প্রবেশ করলে আপনার ব্যক্তিগত তথ্য চুরি হতে পারে। ডাউনলোড লিঙ্ক সবসময় অফিসিয়াল স্টোর থেকে নেয়া উচিত।\n" +
                "\n" +
                "৭. ডিভাইসের নিরাপত্তা সেটিংস\n" +
                "ডিভাইসে সঠিক নিরাপত্তা সেটিংস প্রয়োগ করা অত্যন্ত গুরুত্বপূর্ণ। অজানা উৎস থেকে অ্যাপ ইনস্টল করা নিষিদ্ধ করুন এবং শুধুমাত্র নির্ভরযোগ্য উৎস থেকে অ্যাপ ডাউনলোড করুন।\n" +
                "\n" +
                "৮. ব্যবহারকারীর তথ্য সুরক্ষা\n" +
                "আপনার ব্যক্তিগত তথ্য সুরক্ষিত রাখা গুরুত্বপূর্ণ। যদি অ্যাপটি আপনার তথ্য সংগ্রহ করে, তাহলে সেটি কিভাবে ব্যবহার হবে, তা জানুন এবং প্রয়োজনমতো অনুমতি সীমাবদ্ধ করুন।");
                arrayList.add(hashMap);



        hashMap = new HashMap<>();
        hashMap.put("cat", "Science");
        hashMap.put("image_url", "https://images.prothomalo.com/prothomalo-bangla%2F2024-11-12%2F5fshe1un%2FAfrica.-pexels.jpg?rect=303%2C0%2C3266%2C2177&auto=format%2Ccompress&fmt=webp&dpr=1.0&format=webp&w=700");
        hashMap.put("title", "দুই ভাগ হয়ে যাবে আফ্রিকা মহাদেশ, কীভাবে");
        hashMap.put("des", "পৃথিবীর অন্যতম বড় মহাদেশ আফ্রিকা। তবে ভবিষ্যতে দুই ভাগে বিভক্ত হয়ে যাবে এই মহাদেশ। শুধু তা–ই নয়, আফ্রিকা মহাদেশ ভাগ হয়ে নতুন এক মহাসাগর তৈরি হবে বলে ভবিষ্যদ্বাণী করেছেন বিজ্ঞানীরা। তাঁদের দাবি, ভূপৃষ্ঠের নিচে থাকা টেকটোনিক প্লেটের পরিবর্তনের কারণে আফ্রিকা মহাদেশের ভূখণ্ড ধীরে ধীরে সরে যাওয়ার মাধ্যমে আলাদা হচ্ছে। এ ধারা অব্যাহত থাকলে প্রায় পাঁচ কোটি বছর পরে আফ্রিকা মহাদেশ ভাগ হয়ে একটি নতুন মহাসাগর তৈরি হতে পারে। আফ্রিকা মহাদেশ বিভক্ত হওয়ার ঘটনা শুনতে অবাক লাগলেও এ ধরনের ঘটনা পৃথিবীতে আগেও ঘটেছে। আফ্রিকা ও দক্ষিণ আমেরিকা মহাদেশ একসময় একসঙ্গে যুক্ত ছিল বলে জীবাশ্ম প্রমাণও রয়েছে।");
        arrayList.add(hashMap); hashMap = new HashMap<>();



         hashMap = new HashMap<>();
        hashMap.put("cat", "Tv News");
        hashMap.put("image_url", "https://images.prothomalo.com/prothomalo-bangla%2F2024-11-13%2Fziek0hgk%2FWhatsApp_Image_2023_03_21_at_7_49_22_PM.jpeg?rect=0%2C0%2C960%2C640&auto=format%2Ccompress&fmt=webp&dpr=1.0&format=webp&w=700");
        hashMap.put("title", "এই পোস্ট এডিটেড, আমি কোনো স্ট্যাটাস পোস্ট করিনি: শবনম ফারিয়া");
        hashMap.put("des", "শবনম ফারিয়ার নামে ফেসবুকে যে স্ক্রিনশট ছড়িয়েছে, তাতে ঠিক এ রকম লেখা রয়েছে, ‘আজকে কিছু কথা বলতে চাই। হয়তো আপনাদের ভালো নাও লাগতে পারে। কিন্তু আমি নিরুপায়। আর চুপ করে থাকতে চাই না। ৫ আগস্ট আমরা যেই আশা নিয়ে দ্বিতীয় স্বাধীনতার জন্য উদ্\u200Cযাপন করেছিলাম, সত্যিই কি আমরা সেই স্বাধীনতা পেয়েছি? সেই আমরা তো ছাত্র-জনতার পক্ষে, সারা বাংলার মানুষের কথা চিন্তা করে রাস্তায় নেমেছিলাম। আমরা তো কোনো নির্দিষ্ট রাজনৈতিক দলের পক্ষে রাস্তায় নামিনি। ছাত্র-জনতার আন্দোলনের নামে তাহলে কি আমাদের ধোঁকা দেওয়া হলো? একজন সমন্বয়ককে দেখলাম স্বীকার করে নিল, বিপ্লব সফল করতে মেট্রোরেলে আগুন দেওয়া, পুলিশ হত্যা এসব তাদের পূর্বপরিকল্পিত ছিল। আমার তো এখন সন্দেহ হচ্ছে, এত এত ছাত্র নিহত হলো, বারান্দায়, বাসার ছাদে নিষ্পাপ শিশুগুলো হত্যার শিকার হলো, এসব কি সত্যিই পুলিশের গুলিতেই হয়েছিল?");
        arrayList.add(hashMap); hashMap = new HashMap<>();



    }
}
