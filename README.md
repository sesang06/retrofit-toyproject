# retrofit-toyProject
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

## 소개
![지도 UI](https://github.com/sesang06/retrofit-toyproject/blob/master/retrofit-toyproject-1.jpg?raw=true)


![리사이클러뷰 UI](https://github.com/sesang06/retrofit-toyproject/blob/master/retrofit-toyproject-2.jpg?raw=true)


- 개발 중단 (2017.12.2 ~ 2018.1.26) 토이 프로젝트
- retrofit으로 네트워크로 통신해본 한 후, 리사이클러뷰에 내용물을 뿌려 보는 것을 구현

## 구현내용

- PostActivity
    - 이미지와 함께 글을 서버에 올릴 수 있어요
    - 이미지는 카메라를 통해 올려요
    - 올리기 전에 Gilde로 한번 리사이징 해서 올려요
    ~~~
        Glide.with(this)
                    .load(mCurrentPhotoPath)
                    .into(postImageView);

    ~~~
- ArticleListAcitivity
    - 서버에서 json 형식으로 아티클 목록을 받아 와요
    - 받아온 정보는 recyclerview로 뿌려 줘요
    ~~~
        ServiceHelper.getInstance().getArticles().enqueue(new Callback<List<ArticleModel>>() {
                    @Override
                    public void onResponse(Call<List<ArticleModel>> call, Response<List<ArticleModel>> response) {
                        System.out.println(response.body().size());
                        List<ArticleModel> articleModels = response.body();
                        for (ArticleModel articleModel: articleModels){
                            System.out.println(articleModel.getText());
                        }
                        ArticleListActivity.this.articleModels.clear();
                        ArticleListActivity.this.articleModels.addAll(articleModels);
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<ArticleModel>> call, Throwable t) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    ~~~
- Mapfragment
    - 서버에서 받아온 위치정보를 구글 맵에 마커로 뿌려 줘요
    ~~~
        public void drawMarkers(){
                ServiceHelper.getInstance().getArticles().enqueue(new Callback<List<ArticleModel>>() {
                    @Override
                    public void onResponse(Call<List<ArticleModel>> call, Response<List<ArticleModel>> response) {
                        System.out.println(response.body().size());
                        for (Marker marker : markers){
                            marker.remove();
                        }
                        List<ArticleModel> articleModels = response.body();
                        for (ArticleModel articleModel : articleModels){
                            PointModel pointModel = new PointModel(articleModel.getPointX(), articleModel.getPointY());
                            System.out.println(pointModel.getLatitude()+ "latatat");
                            markers.add(googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(pointModel.getLatitude(), pointModel.getLongitude()))
                                    .title(articleModel.getText())));

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ArticleModel>> call, Throwable t) {

                    }
                });
            }

    ~~~
