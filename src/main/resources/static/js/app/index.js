const index = {
  init() {
    $('#btn-save').on('click', () => {
      this.save();
    });
    $('#btn-update').on('click', () => {
      this.update();
    });
    $('#btn-delete').on('click', () => {
      this.delete();
    });
  },
  save() {
    const data = {
      title : document.getElementById('title').value,
      author: document.getElementById('author').value,
      content: document.getElementById('content').value,
    }

    axios.post('/api/v1/posts', data)
      .then(() => {
        alert('글이 등록되었습니다');
        window.location.href = '/';
      })
      .catch((err) => {
        alert(JSON.stringify(err));
      });
  },
  update() {
    const data = {
      title: document.getElementById('title').value,
      content: document.getElementById('content').value,
    };

    const id = document.getElementById('id').value;
    axios.put(`/api/v1/posts/${id}`, data)
      .then(() => {
        alert('글이 수정되었습니다');
        window.location.href = '/';
      })
      .catch((err) => {
        alert(JSON.stringify(err));
      });
  },
  delete() {
    const id = document.getElementById('id').value;
    axios.delete(`/api/v1/posts/${id}`)
      .then(() => {
        alert('글이 삭제되었습니다');
        window.location.href = '/';
      })
      .catch((err) => {
        alert(JSON.stringify(err));
      })
  },
};

index.init();
